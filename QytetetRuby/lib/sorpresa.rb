#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module ModeloQytetetRuby
  class Sorpresa
    attr_reader :texto,:valor,:tipo
    def initialize(texto,valor,tipo)
      @texto=texto
      @valor=valor
      @tipo=tipo
    end
  
    def to_s
      "@@ Carta Sorpresa: #{@texto} | Valor: #{@valor} @@"
    end
    
  end
end

