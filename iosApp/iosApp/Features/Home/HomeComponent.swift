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

struct HomeViewState {
  let newCategory: [BookCover]
  let curatedCategories: [Category]
}

enum HomeViewAction {
  case selctedBookCover(id: String)
  case selectedCategory(sectionIndex: Int)
}

class HomeViewModel: ViewModel {
  @Published var state: HomeViewState
  var statePublisher: AnyPublisher<HomeViewState, Never> { $state.eraseToAnyPublisher() }
  
  required init(arguments: HomeArguments) {
    state = HomeViewState(newCategory: [], curatedCategories: [])
  }
  
  func handle(action: HomeViewAction) {
    switch action {
    case .selctedBookCover(let id):
      // TODO: Fetch book by id
      guard let book = testBooks.first(where: { $0.id == id }) else { return }
      // TODO: Navigate
    case .selectedCategory(let sectionIndex):
      print("selected category at \(sectionIndex)")
    }
  }
}
