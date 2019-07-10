# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: mmahloko <marvin@42.fr>                    +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2019/07/01 14:29:12 by mmahloko          #+#    #+#              #
#    Updated: 2019/07/10 14:56:50 by mmahloko         ###   ########.fr        #
#                                                                              #
# **************************************************************************** #

package = ./za/co/wethinkcode/avajlauncher/

SRC = 	$(package)Simulator.java \
		$(package)Aircraft.java \
		$(package)AircraftFactory.java \
		$(package)Baloon.java \
		$(package)Coordinates.java \
		$(package)Flyable.java \
		$(package)Helicopter.java \
		$(package)JetPlane.java \
		$(package)Tower.java \
		$(package)weather/WeatherProvider.java \
		$(package)WeatherTower.java \

OBJ = 	$(package)Simulator.class \
		$(package)Aircraft.class \
		$(package)AircraftFactory.class \
		$(package)Baloon.class \
		$(package)Coordinates.class \
		$(package)Flyable.class \
		$(package)Helicopter.class \
		$(package)JetPlane.class \
		$(package)Tower.class \
		$(package)weather/WeatherProvider.class \
		$(package)WeatherTower.class \

all : $(OBJ)

bin : $(OBJ)
	java za.co.wethinkcode.avajlauncher.Simulator scenario.txt

$(package)Simulator.class : $(package)Simulator.java
	javac $(package)Simulator.java

$(package)Aircraft.class : $(package)Aircraft.java
	javac $(package)Aircraft.java

$(package)AircraftFactory.class : $(package)AircraftFactory.java
	javac $(package)AircraftFactory.java

$(package)Coordinates.class : $(package)Coordinates.java
	javac $(package)Coordinates.java
	
$(package)Flyable.class : $(package)Flyable.java
	javac $(package)Flyable.java

$(package)Baloon.class : $(package)Baloon.java
	@javac $(package)Baloon.java
	
$(package)Helicopter.class : $(package)Helicopter.java
	@javac $(package)Helicopter.java
	
$(package)JetPlane.class : $(package)JetPlane.java
	@javac $(package)JetPlane.java

$(package)Tower.class : $(package)Tower.java
	javac $(package)Tower.java

$(package)WeatherTower.class : $(package)WeatherTower.java
	@javac $(package)WeatherTower.java
	
$(package)weather/WeatherProvider.class : $(package)weather/WeatherProvider.java
	javac $(package)weather/WeatherProvider.java
				
re: fclean $(OBJ)

clean:
	@rm $(OBJ)

fclean: clean