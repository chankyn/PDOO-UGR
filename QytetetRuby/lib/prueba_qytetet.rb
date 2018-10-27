#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'sorpresa'
require_relative 'tipo_sorpresa'
require_relative 'titulo_propiedad'
require_relative 'tipo_casilla'
require_relative 'casilla'
require_relative 'tablero'
require_relative 'qytetet'
module ModeloQytetetRuby
  class PruebaQytetet
    @@mazo = Array.new
    @@tablero = Tablero.new
    
    def self.inicializar_sorpresas
      @@mazo<< Sorpresa.new("Gana 500",500, TipoSorpresa::PAGARCOBRAR)
      @@mazo<< Sorpresa.new("Pierde 500",500, TipoSorpresa::PAGARCOBRAR)
      @@mazo<< Sorpresa.new("Ir a la casilla 10",10, TipoSorpresa::IRACASILLA)
      @@mazo<< Sorpresa.new("Ir a la casilla 20",20, TipoSorpresa::IRACASILLA)
      @@mazo<< Sorpresa.new("Ir a la carcel",@@tablero.carcel, TipoSorpresa::IRACASILLA)
      @@mazo<< Sorpresa.new("Gana 100 por casa/hotel",100, TipoSorpresa::PORCASAHOTEL)
      @@mazo<< Sorpresa.new("Pierde 100 por casa/hotel",100, TipoSorpresa::PORCASAHOTEL)
      @@mazo<< Sorpresa.new("Recibe 200 de cada jugador",200, TipoSorpresa::PORJUGADOR)
      @@mazo<< Sorpresa.new("Regala 200 de cada jugador",200, TipoSorpresa::PORJUGADOR)
      @@mazo<< Sorpresa.new("Usando esta carta sales de la carcel",0, TipoSorpresa::SALIRCARCEL)
    end
    def self.obtener_sorpresas_valor_mayor_0
      resultado = Array.new
      @@mazo.each{
        |i|
        if (i.valor>0)
          resultado<<i
        end
      }
      return resultado
    end
    def self.obtener_sorpresas_tiposorpresa(tipo=TipoSorpresa::IRACASILLA)
      resultado = Array.new
      @@mazo.each{
          |i|
          if(i.tipo==tipo)
            resultado<<i
          end
        }
      return resultado
    end
    
    #def self.main
    #  mc = Qytetet.instance
    #  nombres = Array.new
    #  nombres<<"Carlos"
    #  nombres<<"Marisol"
    #  nombres<<"Nazaret"
    #  nombres<<"Paco"
    #  mc.inicializar_juego(nombres)
    #  puts mc.to_s
    #  puts  "-------------------------------------------"
      #puts mc.prueba
      
    #end
  end
  #PruebaQytetet.main
  
end