# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetetRuby
  class TituloPropiedad
    
    attr_reader :nombre,:hipotecada,:alquiler_base,:factor_revalorizacion,:hipoteca_base,:precio_edificar
    attr_writer :hipotecada,:propietario
    attr_accessor :casilla
    
    def initialize(nombre,alquiler_base,factor_revalorizacion,hipoteca_base,precio_edificar)
      @nombre=nombre
      @alquiler_base=alquiler_base
      @factor_revalorizacion=factor_revalorizacion
      @hipoteca_base=hipoteca_base
      @precio_edificar=precio_edificar
      @hipotecada=false
      @propietario = nil
      @casilla = nil
    end
    @override
    def to_s
      "Nombre #{@nombre} \n hipotecada #{@hipotecada} \n alquiler_base #{@alquiler_base} 
        \n factor_revalorizacion #{@factor_revalorizacion} \n hipoteca_base #{@hipoteca_base}
        \n precio_edificar #{@precio_edificar}"
    end
    def tengo_propietario
      return @propietario!=nil
    end
    def propietario_encarcelado
      return @propietario.encarcelado
    end
    def cobrar_alquiler(coste)
      @propietario.modificar_saldo(-coste)
    end
  end
end
