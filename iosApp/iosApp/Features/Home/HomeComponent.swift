//
//  HomeComponent.swift
//  iosApp
//
//  Created by Sergey Prybysh on 8/31/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Foundation
import Shared

// MARK: Component

struct HomeArguments {}
class HomeComponent: Component<HomeArguments, Void, HomeViewModel, HomeView> {}

// MARK: MVVM

class HomeViewModel: ViewModel {  
  struct State {
    let newCategory: [BookCover]
    let curatedCategories: [Category]
  }

  enum Action {
    case selctedBookCover(id: String)
    case selectedCategory(sectionIndex: Int)
  }
  
  @Published var state: State
  var statePublisher: AnyPublisher<State, Never> { $state.eraseToAnyPublisher() }
  
  private weak var services: Services?
  
  required init(arguments: HomeArguments, services: Services) {
    self.services = services
    state = State(
      newCategory: [testBooks[0], testBooks[1], testBooks[2], testBooks[3]],
      curatedCategories: [
        Category(name: "Category one", books: [testBooks[0], testBooks[1], testBooks[2], testBooks[3]]),
        Category(name: "Category two", books: [testBooks[0], testBooks[1], testBooks[2]]),
      ])
  }
  
  func handle(action: Action) {
    switch action {
    case .selctedBookCover(let id):
      guard let services else { return }
      let bookDetailsComponent = BookDetailsComponent(
        arguments: BookDetailsArguments(bookID: id),
        services: services,
        delegate: nil)
      services.router.homePath.append(bookDetailsComponent)
    case .selectedCategory(let sectionIndex):
      print("selected category at \(sectionIndex)")
    }
  }
}
