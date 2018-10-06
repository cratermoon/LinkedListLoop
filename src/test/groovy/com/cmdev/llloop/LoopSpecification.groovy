package com.cmdev.llloop

import spock.lang.*

class LinkedListSpecification extends Specification {

  def n

  @Unroll("Node constructed with #i has Value #x")
  def "Nodes have a value #x when constructed  with #i" () {

   given:
        n = new Node(i)
   expect:
        n.getValue() == x

    where:
        i | x
        1 | 1 
        5 | 5
        10 | 10
        Integer.MAX_VALUE | Integer.MAX_VALUE
        -3 | -3
        0 | 0
        Integer.MIN_VALUE | Integer.MIN_VALUE
    }
}
