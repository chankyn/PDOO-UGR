#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require "singleton"
require_relative "jugador.rb"
require_relative "especulador.rb"
require_relative "dado.rb"
require_relative "tablero.rb"
require_relative "tipo_sorpresa"
module ModeloQytetetRuby
  class Qytetet
    #Indicamos que es singleton.
    include Singleton
    #Get y set
    attr_reader :carta_actual,:jugador_actual,:jugadores,:SALDO_SALIDA
    
    @@MAX_JUGADORES = 4
    @@MAX_CARTAS = 12
    @@MAX_CASILLAS = 20
    @@PRECIO_LIBERTAD = 200
    @@SALDO_SALIDA = 1000
    def initialize
      
    end
    def inicializar_juego(nombres_jugadores)
      #assert(nombres_jugadores.size>=2 && nombres_jugadores.size<=@@MAX_JUGADORES)
      @@MAX_JUGADORES = nombres_jugadores.size
      
      @jugadores = Array.new
      @mazo = Array.new
      
      inicializar_tablero
      inicializar_jugadores(nombres_jugadores)
      inicializar_cartas_sorpresa
      salida_jugadores
      
      @carta_actual = @mazo[rand(0..@@MAX_CARTAS-1)]
      @dado = Dado.instance
    end
    def self.saldo_salida
      return @@SALDO_SALIDA
    end
    def aplicar_sorpresa
      tiene_propietario = false
      tipo = @carta_actual.tipo
      if (tipo == TipoSorpresa::PAGARCOBRAR)
        @jugador_actual.modificar_saldo(@carta_actual.valor)
      elsif (@carta_actual.tipo == TipoSorpresa::IRACASILLA)
          if(@tablero.es_casilla_carcel(@carta_actual.valor))
            puts "Ha tocado la sorpresa: "+@carta_actual.to_s
            puts "El jugador "+@jugador_actual.nombre+" va la carcel!"
            encarcelar_jugador
          else
            nueva_casilla = @tablero.obtener_casilla_numero(@carta_actual.valor)
            tiene_propietario = @jugador_actual.actualizar_posicion(nueva_casilla)
            puts "El jugador "+@jugador_actual.nombre+" va a la casilla "+@jugador_actual.casilla_actual.numero_casilla.to_s
          end
      elsif(@carta_actual.tipo == TipoSorpresa::PORCASAHOTEL)
          @jugador_actual.pagar_cobrar_por_casa_y_hotel(@carta_actual.valor)
      elsif(@carta_actual.tipo == TipoSorpresa::PORJUGADOR)
          for jugador in @jugadores
            if (jugador!=@jugador_actual)
              jugador.modificar_saldo(@carta_actual.valor)
            end
            @jugador_actual.modificar_saldo(-@carta_actual.valor)
          end
      elsif (@carta_actual.tipo == TipoSorpresa::CONVERTIRME)
        posicion_jugador = @jugadores.rindex(@jugador_actual)
        @jugador_actual = @jugador_actual.convertirme(@carta_actual.valor)
        @jugadores[posicion_jugador] = @jugador_actual
 
      end
      if(@carta_actual.tipo == TipoSorpresa::SALIRCARCEL)
        @jugador_actual.carta_libertad=@carta_actual
        @mazo.delete(@mazo.rindex(@carta_actual))
      else
        @carta_actual = @mazo[rand(0..@@MAX_CARTAS-1)]
      end
      return tiene_propietario
    end
    def cancelar_hipoteca(casilla)
      realizado = false
      if(casilla.esta_hipotecada)
        if(@jugador_actual.puedo_pagar_hipoteca(casilla))
          @jugador_actual.modificar_saldo(-casilla.calcular_valor_hipoteca)
          realizado = true
          casilla.titulo.hipotecada = false
        end
      end
      return realizado
    end
    def comprar_titulo_propiedad
      return @jugador_actual.comprar_titulo
    end
    def hipotecar_propiedad(casilla)
      se_puede_edificar = false
      puedo_hipotecar = false
      if (casilla.soy_edificable)
        se_puede_edificar = !casilla.esta_hipotecada
        if(se_puede_edificar)
          puedo_hipotecar = @jugador_actual.puedo_hipotecar(casilla)
          if(puedo_hipotecar)
            cantidad_recibida = casilla.hipotecar
            @jugador_actual.modificar_saldo(cantidad_recibida)
          end
        end
      end
      return puedo_hipotecar
    end
    def intentar_salir_carcel(metodo)
      libre = false
      if (metodo == MetodoSalirCarcel::TIRANDODADO)
        valor_dado = @dado.tirar
        if (valor_dado>5) 
          libre = true 
        end
      else
        tengo_saldo = @jugador_actual.pagar_libertad(-@@PRECIO_LIBERTAD)
        libre = tengo_saldo
      end
      if (libre)
        @jugador_actual.encarcelado = false
      end
      return libre
    end
    def jugar
      tiene_propietario = false
      valor_dado = @dado.tirar
      puts "El jugador saca "+valor_dado.to_s+" al tirar el dado."
      casilla_posicion = @jugador_actual.casilla_actual
      nueva_casilla = @tablero.obtener_nueva_casilla(casilla_posicion,valor_dado)
      tiene_propietario = @jugador_actual.actualizar_posicion(nueva_casilla)
      if (!nueva_casilla.soy_edificable)
        if(nueva_casilla.tipo == TipoCasilla::JUEZ)
          encarcelar_jugador
        else
          if(nueva_casilla.tipo == TipoCasilla::SORPRESA)
            #@carta_actual = @mazo[rand(0..@@MAX_CARTAS-1)]
            #@carta_actual = @mazo[10]
            aplicar_sorpresa
          end
        end
      end
      return tiene_propietario
    end
    def obtener_ranking
      ranking = Array.new
      for jugador in @jugadores
        capital = jugador.obtener_capital
        ranking<<[jugador.nombre,capital]
      end
      return ranking
    end
    def vender_propiedad(casilla)
      vendido = false
      if (casilla.soy_edificable)
        puedo_vender = @jugador_actual.puedo_vender_propiedad(casilla)
        if(puedo_vender)
          @jugador_actual.vender_propiedad(casilla)
          vendido = true
        end
      end
      return vendido
    end
    def encarcelar_jugador
      if(!@jugador_actual.tengo_carta_libertad)
        casilla_carcel = @tablero.obtener_casilla_numero(@tablero.carcel)
        @jugador_actual.ir_a_carcel(casilla_carcel)
      else
        carta = @jugador_actual.devolver_carta_libertad
        @mazo<<carta
      end
    end
    
    @override
    def to_s
     puts "Cartas Sorpresa: \n"
     @mazo.each{
        |i|
        puts i.to_s
      }
      puts "Tablero: \n"
      @tablero.to_s
      puts "Jugadores: \n"
      @jugadores.each { 
        |i|
        puts i.to_s
      }
    end
    
    #Inicializamos el mazo con las cartas sorpresa.
    def inicializar_cartas_sorpresa
      
      @mazo<< Sorpresa.new("Gana 500",500, TipoSorpresa::PAGARCOBRAR)
      @mazo<< Sorpresa.new("Pierde 500",-500, TipoSorpresa::PAGARCOBRAR)
      @mazo<< Sorpresa.new("Ir a la casilla 10",10, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Ir a la casilla 15",15, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Ir a la carcel",@tablero.carcel, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Gana 100 por casa/hotel",100, TipoSorpresa::PORCASAHOTEL)
      @mazo<< Sorpresa.new("Pierde 100 por casa/hotel",-100, TipoSorpresa::PORCASAHOTEL)
      @mazo<< Sorpresa.new("Recibe 200 de cada jugador",200, TipoSorpresa::PORJUGADOR)
      @mazo<< Sorpresa.new("Regala 200 a cada jugador",-200, TipoSorpresa::PORJUGADOR)
      @mazo<< Sorpresa.new("Usando esta carta sales de la carcel",0, TipoSorpresa::SALIRCARCEL)
      @mazo<< Sorpresa.new("CARTA ESPECULADOR 1.",3000,TipoSorpresa::CONVERTIRME)
      @mazo<< Sorpresa.new("CARTA ESPECULADOR 2.",5000,TipoSorpresa::CONVERTIRME)
      
    end
    
    def inicializar_jugadores(nombres)
      #Falta comprobar que el array de nombres debe estar entre 2 y maxjugadores.
      #Recorremos el array pasado como parámetro con los nombres de los jugadores.
        nombres.each{
          |nombre|
          #Vamos añadiendo al array jugadores todos los jugadores pasados por parámetro.
          @jugadores<<Jugador.new(nombre,@tablero.obtener_casilla_numero(0))
        }
    end
    def inicializar_tablero
      #Inicializamos el tablero
      @tablero = Tablero.new
    end
    def salida_jugadores
      @jugadores.each{
        |jugador|
        jugador.casilla_actual = @tablero.obtener_casilla_numero(0)
        jugador.saldo = 7500
      }
       
      @jugador_actual = @jugadores[rand(0..@@MAX_JUGADORES-1)]
      
    end
    def siguiente_jugador
      posicion_jugador = @jugadores.rindex(@jugador_actual)
      if (@jugador_actual == @jugadores[@@MAX_JUGADORES-1])
        @jugador_actual = @jugadores[0]
      else
        @jugador_actual = @jugadores[posicion_jugador+1]
      end
    end
    def propiedades_hipotecadas_jugador(hipotecadas)
      resultado = Array.new
      propiedades = Array.new
      if @jugador_actual.tengo_propiedades
        propiedades = @jugador_actual.obtener_propiedades_hipotecas(hipotecas)
        propiedades.each{
          |i|
          resultado.add(i.casilla)
        }
      end
      return resultado
    end
    def edificar_casa(casilla)
      puedo_edificar = false
      if (casilla.soy_edificable)
        se_puede_edificar = casilla.se_puede_edificar_casa(@jugador_actual.factor_especulador)
        if(se_puede_edificar)
          if (@jugador_actual.puedo_edificar_casas(casilla))
            coste_edificar = casilla.edificar_casa
            @jugador_actual.modificar_saldo(-coste_edificar)
            puedo_edificar = true
          end
        end
      end
      return puedo_edificar
    end
    
    def edificar_hotel(casilla)
      puedo_edificar = false
      if (casilla.soy_edificable)
        se_puede_edificar = casilla.se_puede_edificar_hotel(@jugador_actual.factor_especulador)
        if(se_puede_edificar)
          if (@jugador_actual.puedo_edificar_hoteles(casilla))
            coste_edificar = casilla.edificar_hotel
            @jugador_actual.modificar_saldo(-coste_edificar)
            puedo_edificar = true
          end
        end
      end
      return puedo_edificar
    end
    private :inicializar_cartas_sorpresa,:inicializar_jugadores,:inicializar_tablero,:salida_jugadores,:encarcelar_jugador
    #public
    #def prueba
    #  @tablero.obtener_casilla_numero(2).titulo
    #end
  end
end
