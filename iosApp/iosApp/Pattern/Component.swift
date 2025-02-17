//
//  Component.swift
//  iosApp
//
//  Created by Sergey Prybysh on 8/31/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Foundation
import SwiftUI

class Store<State, Action>: ObservableObject {
  @Published fileprivate(set) var state: State
  private var handler: ((Action) -> Void)?

  public init(initialState: State, handler: ((Action) -> Void)?) {
    self.state = initialState
    self.handler = handler
  }

  public func handle(_ action: Action) {
    handler?(action)
  }
}

protocol ComponentView: View {
  associatedtype State
  associatedtype Action
  init(store: Store<State, Action>)
}

class Component<Arguments, VModel: ViewModel, Content: ComponentView>: Hashable
where VModel.Arguments == Arguments, VModel.State == Content.State, VModel.Action == Content.Action {
  private let id = UUID()
  private let viewModel: VModel
  private let store: Store<Content.State, Content.Action>
  private(set) var view: Content
  
  func hash(into hasher: inout Hasher) {
    hasher.combine(id)
  }
  
  static func == (lhs: Component<Arguments, VModel, Content>, rhs: Component<Arguments, VModel, Content>) -> Bool {
    lhs.id == rhs.id
  }

  private var cancellable = Set<AnyCancellable>()

  init(arguments: Arguments, services: Services) {
    let viewModel = VModel(arguments: arguments, services: services)
    self.viewModel = viewModel
    let store = Store(
      initialState: viewModel.state,
      handler: { action in
        viewModel.handle(action: action)
      })

    self.store = store

    view = Content(store: store)

    self.viewModel
      .statePublisher
      .sink { [weak self] state in
        guard let self = self else { return }
        self.store.state = state
      }
      .store(in: &cancellable)
  }
}
