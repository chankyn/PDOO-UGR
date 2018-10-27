# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'qytetet.rb'
module ModeloQytetetRuby
  class Jugador

    attr_accessor :casilla_actual,:encarcelado,:carta_libertad,:saldo,:nombre,:propiedades,:factor_especulador

    def initialize(nombre,casilla_actual,encarcelado = false,saldo = 7500,carta_libertad = nil,propiedades = Array.new,factor_especulador = 1)
      @encarcelado = encarcelado
      @nombre = nombre
      @saldo = saldo
      @carta_libertad = carta_libertad
      @casilla_actual = casilla_actual
      @propiedades = Array.new
      @factor_especulador = factor_especulador
      
    end
    @Override
    def to_s
      "@@ nombre del jugador: #{@nombre} | saldo: #{@saldo} | encarcelado? #{@encarcelado} | carta de libertad? #{@carta_libertad} @@"
    end
    def devolver_carta_libertad
      assert(@carta_libertad!=nil)
      aux = @carta_libertad
      @carta_libertad = nil
      return aux
    end
    def modificar_saldo(cantidad)
      @saldo=@saldo + cantidad
    end
    def obtener_capital
      capital = @saldo
      @propiedades.each{
        |i|
        if !i.hipotecada
          valor_propiedad = i.alquier_base + i.precio_edificar * (i.casilla.num_casas+i.casilla.num_hoteles)
          capital += valor_propiedad
        else
          capital -= valor_propiedad
        end
      }
      return capital
    end
    def pagar_cobrar_por_casa_y_hotel(cantidad)
      numero_total = cuantas_casas_hoteles_tengo;
      modificar_saldo(numero_total*cantidad);
    end
    def puedo_vender_propiedad(casilla)
      if es_de_mi_propiedad(casilla) && !casilla.esta_hipotecada
        return true
      end
      return false
    end

    def cuantas_casas_hoteles_tengo
      total = 0
      @propiedades.each{
        |i|
        casilla = i.casilla
        if casilla != nil
          total += casilla.num_casas + casilla.num_hoteles
        end
      }
      return total
    end
    def es_de_mi_propiedad(casilla)
      encontrado = false
      for propiedad in @propiedades
        if (propiedad.casilla == casilla)
          encontrado = true
        end
      end
      return encontrado
    end

    def eliminar_de_mis_propiedades(casilla)
      for i in @propiedades
        if(i.casilla == casilla)
          @propiedades.delete(i)
        end
      end
    end
    def obtener_propiedades_hipotecadas(hipotecadas)
      resultado = Array.new
      @propiedades.each{
        |i|
        if i.casilla.esta_hipotecada == hipotecadas
          resultado.add(i)
        end
      }
      return resultado
    end
    def obtener_propiedades
      casilla_propiedades = Array.new
      for titulo in @propiedades
        casilla_propiedades << titulo.casilla
      end
      return casilla_propiedades
    end
    
    def tengo_propiedades
      return @propiedades.size>0
    end
    
    def tengo_saldo(cantidad)
      return @saldo>=cantidad
    end
    def puedo_edificar_casas(casilla)
      es_mia = es_de_mi_propiedad(casilla)
      tengo_saldo = false
      if (es_mia)
        coste_edificar_casas = casilla.titulo.precio_edificar
        tengo_saldo = tengo_saldo(coste_edificar_casas)
      end
      return tengo_saldo
    end
    def puedo_edificar_hoteles(casilla)
      es_mia = es_de_mi_propiedad(casilla)
      tengo_saldo = false
      if (es_mia)
        coste_edificar_hoteles = casilla.titulo.precio_edificar
        tengo_saldo = tengo_saldo(coste_edificar_hoteles)
      end
      return tengo_saldo
    end
    
    def comprar_titulo
      puedo_comprar = false
      if(@casilla_actual.soy_edificable)
        tengo_propietario = @casilla_actual.tengo_propietario
        if (!tengo_propietario)
          coste_compra = @casilla_actual.coste
          if (coste_compra <= saldo)
            titulo = @casilla_actual.asignar_propietario(self)
            @propiedades<<titulo
            modificar_saldo(-coste_compra)
            puedo_comprar = true
          end
        end
      end
      return puedo_comprar
    end
    def ir_a_carcel(casilla)
      @casilla_actual = casilla
      @encarcelado = true
    end
    def devolver_carta_libertad
      aux = @carta_libertad
      @carta_libertad = nil
      return aux
    end
    def obtener_capital
      capital = @saldo
      for titulo in @propiedades
        if(!titulo.hipotecada)
          valor_propiedad = titulo.alquiler_base+titulo.precio_edificar*(titulo.casilla.num_casas+titulo.casilla.num_hoteles)
          capital = capital + valor_propiedad
        else
          capital = capital - titulo.hipoteca_base
        end
      end
      return capital
    end
    def pagar_libertad(precio_libertad)
      tengo_saldo = tengo_saldo(precio_libertad)
      if (tengo_saldo)
        modificar_saldo(precio_libertad)
      end
      return tengo_saldo
    end
    def puedo_hipotecar(casilla)
      return es_de_mi_propiedad(casilla)
    end
    def puedo_pagar_hipoteca(casilla)
      return tengo_saldo(casilla.calcular_valor_hipoteca)
    end
    def tengo_carta_libertad
      return @carta_libertad!=nil
    end
    def obtener_propiedades_hipotecadas(hipotecadas)
      resultado = Array.new
      for titulo in @propiedades
        if(titulo.casilla.esta_hipoteca == hipotecadas)
          resultado<<titulo
        end
      end
      return resultado
    end
    def vender_propiedad(casilla)
      precio_venta = casilla.vender_titulo
      modificar_saldo(precio_venta)
      eliminar_de_mis_propiedades(casilla)
    end
    def actualizar_posicion(casilla)
      tengo_propietario = false
      if(casilla.numero_casilla < @casilla_actual.numero_casilla)
        modificar_saldo(Qytetet.saldo_salida)
      end
      @casilla_actual = casilla
      if(casilla.soy_edificable)
        tengo_propietario = casilla.tengo_propietario
        if (tengo_propietario)
          @encarcelado = casilla.propietario_encarcelado
          if (!@encarcelado)
            if (!casilla.esta_hipotecada)
              modificar_saldo(-casilla.cobrar_alquiler)
            end
          end
        end
        if(casilla.tipo == TipoCasilla::IMPUESTO)
          coste = casilla.coste
          modificar_saldo(-coste)
        end
      end
      return tengo_propietario
    end
    def pagar_impuestos(cantidad)
      modificar_saldo(-cantidad);
    end
    
    def convertirme(fianza)
      especulador = Especulador.new(self,fianza)
      especulador.convertirme(fianza);
      @propiedades.each{
        |propiedad|
        propiedad.propietario=especulador;
      }
      
      return especulador;
    end
    private :cuantas_casas_hoteles_tengo,:es_de_mi_propiedad,:eliminar_de_mis_propiedades,:tengo_saldo
  end
end
