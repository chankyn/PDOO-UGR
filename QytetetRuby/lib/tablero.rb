# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'casilla'
require_relative 'calle'
require_relative 'tipo_casilla'
require_relative 'titulo_propiedad'
require_relative 'tipo_sorpresa'
module ModeloQytetetRuby
  class Tablero
    attr_reader :carcel
    def initialize
      @casillas = Array.new
      @casillas<< Casilla.new(0,TipoCasilla::SALIDA)
      @casillas<< Calle.new(1,100,TituloPropiedad.new("Calle 1", 250, -0.1, 50, 350))
      @casillas<< Calle.new(2,150,TituloPropiedad.new("Calle 2", 280, -0.15, 60, 250))
      @casillas<< Casilla.new(3,TipoCasilla::SORPRESA)
      @casillas<< Calle.new(4,200,TituloPropiedad.new("Calle 4", 310, -0.25, 50, 150))
      @casillas<< Casilla.new(5,TipoCasilla::CARCEL)
      @casillas<< Calle.new(6,250,TituloPropiedad.new("Calle 6", 350, -1.1, 70, 400))
      @casillas<< Calle.new(7,300,TituloPropiedad.new("Calle 7", 390, -0.5, 70, 650))
      @casillas<< Casilla.new(8,TipoCasilla::SORPRESA)
      @casillas<< Calle.new(9,350,TituloPropiedad.new("Calle 9", 430, 0.7, 60, 405))
      @casillas<< Casilla.new(10,TipoCasilla::PARKING)
      @casillas<< Calle.new(11,400,TituloPropiedad.new("Calle 11", 475, 0.14, 50, 255))
      @casillas<< Calle.new(12,450,TituloPropiedad.new("Calle 12", 520, 0.29, 60, 850))
      @casillas<< Casilla.new(13,TipoCasilla::SORPRESA)
      @casillas<< Calle.new(14,500,TituloPropiedad.new("Calle 14", 565, 0.02, 80, 475))
      @casillas<< Casilla.new(15,TipoCasilla::JUEZ)
      @casillas<< Calle.new(16,600,TituloPropiedad.new("Calle 16", 625, 0.03, 60, 500))
      @casillas<< Calle.new(17,700,TituloPropiedad.new("Calle 17", 690, 1, 100, 1000))
      @casillas<< Casilla.new(18,TipoCasilla::IMPUESTO)
      @casillas<< Calle.new(19,800,TituloPropiedad.new("Calle 19", 750, 1.05, 100, 500))
      
      @carcel = @casillas[5].numero_casilla
    end
    @override
    def to_s
      "Carcel: #{@carcel} \n Casillas: \n"
      @casillas.each{
        |i|
        puts i.to_s
      }
    end
    
    def obtener_casilla_numero(numero_casilla)
      return @casillas[numero_casilla]
    end
    def es_casilla_carcel(numero_casilla)
      return numero_casilla==@carcel
    end
    def obtener_nueva_casilla(casilla,desplazamiento)
      posicion = casilla.numero_casilla + desplazamiento
      if (posicion < 20)
        return obtener_casilla_numero(posicion)
      else
        return obtener_casilla_numero(posicion-20)
      end
    end
  end
end
