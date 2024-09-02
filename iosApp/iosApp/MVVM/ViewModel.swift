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
  
  init(arguments: Arguments)
  
  func handle(action: Action) -> Void
}

extension ViewModel {
  func reconfigure(_ mutation: (inout State) -> Void) {
    var newState = state
    mutation(&newState)
    state = newState
  }  
}
