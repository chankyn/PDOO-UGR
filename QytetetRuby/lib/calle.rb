# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetetRuby
  class Calle < Casilla
    attr_accessor :num_hoteles, :num_casas, :titulo
    
    def initialize(numero_casilla,coste,titulo)
      super(numero_casilla,coste,TipoCasilla::CALLE)
      
      @num_hoteles=0
      @num_casas=0
      @titulo = titulo
      
      if(@titulo!=nil)
          @titulo.casilla = self
      end
      
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
  end
end
