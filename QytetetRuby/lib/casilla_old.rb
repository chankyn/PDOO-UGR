# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'sorpresa'
module ModeloQytetetRuby
  class Casilla_old
    
    attr_reader :numero_casilla,:coste,:num_hoteles,:tipo
    attr_accessor :titulo,:num_hoteles,:num_casas
 
    def initialize(numero_casilla,coste,tipo,titulo)
      @numero_casilla=numero_casilla
      @coste=coste
      @num_hoteles=0
      @num_casas=0
      @tipo=tipo
      @titulo=titulo
      if(@titulo!=nil)
          @titulo.casilla = self
      end
    end
    
    @override
    def to_s
      "@@ numero_casilla: #{@numero_casilla} | nombre: #{@titulo.nombre} | precio: #{@titulo.alquiler_base} | precio por edificar: #{@titulo.precio_edificar} @@"
    end
    
    def self.crear_calle(numero_casilla,coste,titulo)
      new(numero_casilla,coste,TipoCasilla::CALLE,titulo)
      
    end
    
    def self.crear_casilla(numero_casilla,tipo)
      new(numero_casilla,500,tipo,nil)
    end
    
    def soy_edificable
      return self.tipo == TipoCasilla::CALLE
    end
    
    def esta_hipotecada
      return @titulo.hipotecada
    end
    
    def asignar_propietario(jugador)
      @titulo.propietario=jugador
      return @titulo
    end
    def calcular_valor_hipoteca
      return Integer(@titulo.hipoteca_base + @num_casas * 0.5 * @titulo.hipoteca_base + @num_hoteles * @titulo.hipoteca_base)
    end
    def tengo_propietario
      return @titulo.tengo_propietario
    end
    def cobrar_alquiler
      coste_alquiler_base = @titulo.alquiler_base
      coste_alquiler = coste_alquiler_base + Integer(@num_casas * 0.5 + @num_hoteles * 2)
      @titulo.cobrar_alquiler(-coste_alquiler)
      return coste_alquiler
    end
    def edificar_casa
      @num_casas = @num_casas + 1
      coste_edificar = @titulo.precio_edificar
      return coste_edificar
    end
    def edificar_hotel
      @num_hoteles = @num_hoteles + 1
      coste_edificar = @titulo.precio_edificar
      return coste_edificar
    end
    def hipotecar
      @titulo.hipotecada=true
      cantidad_recibida = calcular_valor_hipoteca
      return cantidad_recibida
    end
    def propietario_encarcelado
      return @titulo.propietario_encarcelado
    end
    def se_puede_edificar_casa(factor_especulador)
      return @num_casas<4*factor_especulador
    end
    def se_puede_edificar_hotel(factor_especulador)
      return @num_hoteles<4*factor_especulador
    end
    def vender_titulo
      precio_compra = Integer(@coste + (@num_casas + @num_hoteles) * @titulo.precio_edificar)
      precio_venta = Integer(precio_compra + @titulo.factor_revalorizacion * precio_compra)
      @titulo.propietario=nil
      @num_casas = 0
      @num_hoteles = 0
      return precio_venta
    end
    def se_puede_edificar_casa(factor_especulador)
      return @num_casas<4*factor_especulador
    end
    def se_puede_edificar_hotel(factor_especulador)
      return @num_hoteles<4*factor_especulador
    end
    private :titulo=
   end
end
