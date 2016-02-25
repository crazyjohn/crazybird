package com.crazybird.test

object TypeTest extends App {
  class Food
  abstract class Animal {
    type FoodType <: Food
    def eat(food: FoodType)
  }
  
  class Grass extends Food
  // compiler error
//  class Cow extends Animal {
//    override def eat(food: Grass)
//  }
  
  class Cow extends Animal {
    type FoodType = Grass
    override def eat(food: Grass) {}
  }
}