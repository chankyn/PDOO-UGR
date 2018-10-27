# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'sorpresa'
module ModeloQytetetRuby
  class Casilla
    
    attr_reader :numero_casilla,:coste,:tipo
    attr_accessor :titulo,:num_hoteles,:num_casas
 
    def initialize(numero_casilla,coste = 0,tipo)
      @numero_casilla = numero_casilla
      @coste = coste
      @tipo = tipo
    end
    
    @override
    def to_s
      "@@ numero_casilla: #{@numero_casilla} | nombre: #{@titulo.nombre} | precio: #{@titulo.alquiler_base} | precio por edificar: #{@titulo.precio_edificar} @@"
    end
    
    def soy_edificable
      return self.tipo == TipoCasilla::CALLE
    end
  end 
end
