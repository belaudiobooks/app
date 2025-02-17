//
//  BookCollectionComponent.swift
//  iosApp
//
//  Created by Sergey Prybysh on 11/18/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Foundation

struct BookCollectionArguments {
  let categoryID: Int64
}

class BookCollectionComponent: Component<BookCollectionArguments, BookCollectionViewModel, BookCollectionView> {}

class BookCollectionViewModel: ViewModel {
  struct State {
    var books: [BookCover]
  }
  
  enum Action {
    case selctedBookCover(id: String)
  }
  
  @Published var state: State
  var statePublisher: AnyPublisher<State, Never> { $state.eraseToAnyPublisher() }
  
  private let services: Services
  
  required init(arguments: BookCollectionArguments,  services: Services) {
    state = State(books: [])
    self.services = services
    
    Task {
      for await books in services.repositoryClient.repository.getBooksDetailsByTagId(id: arguments.categoryID) {
        await MainActor.run {
          self.state.books = books.map { BookDetails(
            bookDetails: $0,
            colorProvider: services.colorProvider.colors)
          .bookCover
          }
        }
      }
    }
  }
  
  func handle(action: Action) {
    switch action {
    case .selctedBookCover(let id):
      let arguments = BookDetailsArguments(bookID: id)
      let component = BookDetailsComponent(arguments: arguments, services: services)
      services.router.homePath.append(component)
    }
  }
}
