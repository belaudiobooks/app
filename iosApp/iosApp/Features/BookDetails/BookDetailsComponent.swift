//
//  BookDetailsComponent.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Foundation
import Shared

// MARK: Component

struct BookDetailsArguments {
  let bookID: String
}

class BookDetailsComponent: Component<BookDetailsArguments, Void, BookDetailsViewModel, BookDetailsView> {}

// MARK: MVVM

class BookDetailsViewModel: ViewModel {
  struct State: Equatable {
    let book: BookCover
  }

  enum Action {
  }
  
  @Published var state: State
  var statePublisher: AnyPublisher<State, Never> { $state.eraseToAnyPublisher() }
  
  private weak var services: Services?
  
  required init(arguments: BookDetailsArguments, services: Services) {
    self.services = services
    state = State(book: testBooks.first(where: {$0.id == arguments.bookID }) ?? testBooks[0])
  }
  
  func handle(action: Action) {
  }
}
