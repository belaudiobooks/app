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
    var book: BookDetails?
  }

  enum Action {
  }
  
  @Published var state: State
  var statePublisher: AnyPublisher<State, Never> { $state.eraseToAnyPublisher() }
  
  private let services: Services
  private let arguments: BookDetailsArguments
  
  required init(arguments: BookDetailsArguments, services: Services) {
    self.services = services
    self.arguments = arguments
    state = State(book: nil)
    setObservations()
  }
  
  func handle(action: Action) {
  }
  
  private func setObservations() {
    Task {
      for await bookDetails in services.repositoryClient.repository.getBookDetails(bookUuid: self.arguments.bookID) {
        state.book = BookDetails(bookDetails: bookDetails, colorProvider: services.colorProvider.colors)
      }
    }
  }
}
