//
//  ViewModel.swift
//  iosApp
//
//  Created by Sergey Prybysh on 8/31/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Foundation

protocol ViewModel: ObservableObject {
  associatedtype Arguments
  associatedtype State
  associatedtype Action
  
  var state: State { get set }
  var statePublisher: AnyPublisher<State, Never> { get }
  
  init(arguments: Arguments,  services: Services)
  
  func handle(action: Action) -> Void
}

extension ViewModel {
  func reconfigure(_ mutation: (inout State) -> Void) {
    var newState = state
    mutation(&newState)
    state = newState
  }  
}


public protocol MutableUnorderedCollection {
  associatedtype Element
  func add(element: Element)
  func remove(element: Element)
  func count(for element: Element)
}

public struct Multiset<E>: MutableUnorderedCollection {
  private var storage: [E: UInt] = [:]
  
  public func add(element: E) {
    storage[element: default: 0] += 1
  }
  
  public func remove(element: E) {
    
  }
  
  public func count(for element: E) {
    
  }
}
