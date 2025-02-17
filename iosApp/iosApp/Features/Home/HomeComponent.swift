//
//  HomeComponent.swift
//  iosApp
//
//  Created by Sergey Prybysh on 8/31/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Foundation

// MARK: Component

struct HomeArguments {}
class HomeComponent: Component<HomeArguments, HomeViewModel, HomeView> {}

// MARK: MVVM

class HomeViewModel: ViewModel {
  struct State {
    var newCategory: [BookCover]
    var curatedCategories: [Category]
  }

  enum Action {
    case selctedBookCover(id: String)
    case selectedCategory(sectionIndex: Int)
  }
  
  /// Mapping Index of the categgory to the corresponding TagID
  private let categoryMap: [Int: Int64] = [0:8, 1:2, 2:7, 3:9]
  
  @Published var state: State
  var statePublisher: AnyPublisher<State, Never> { $state.eraseToAnyPublisher() }
  
  private let services: Services
    
  required init(arguments: HomeArguments, services: Services) {
    self.services = services
    state = State(
      newCategory: [],
      curatedCategories: [
      Category(name: "Modern", books: []),
      Category(name: "Classic", books: []),
      Category(name: "Kids", books: []),
      ])
    
    Task {
      try await services.repositoryClient.repository.refreshData()
    }
    
    observeNewBooks()
    observeModernBooks()
    observeClassicBooks()
    observeKidsBooks()
  }
  
  func handle(action: Action) {
    switch action {
    case .selctedBookCover(let id):
      let bookDetailsComponent = BookDetailsComponent(
        arguments: BookDetailsArguments(bookID: id),
        services: services)
      services.router.homePath.append(bookDetailsComponent)
    case .selectedCategory(let sectionIndex):
      let categoryID = categoryMap[sectionIndex]!
      let bookCollectionComponent = BookCollectionComponent(
        arguments: BookCollectionArguments(categoryID: categoryID),
        services: services)
      services.router.homePath.append(bookCollectionComponent)
    }
  }
  
  private func observeNewBooks() {
    Task {
      for await bookCovers in services.repositoryClient.repository.getNLatestNarrationsAsBookCovers(numberOfBookCovers: categoryMap[0]!) {
        await MainActor.run {
          self.state.newCategory = bookCovers.map { .init(bookCover: $0, gradientColorProvider: self.services.colorProvider.colors) }
        }
      }
    }
  }
  
  private func observeModernBooks() {
    Task {
      for await modernBooks in services.repositoryClient.repository.getBooksDetailsByTagId(id: categoryMap[1]!) {
        await MainActor.run {
          self.state.curatedCategories[0].books = Array(modernBooks.map { BookDetails(
            bookDetails: $0,
            colorProvider: self.services.colorProvider.colors)
            .bookCover }.prefix(8))
        }
      }
    }
  }
  
  private func observeClassicBooks() {
    Task {
      for await classicBooks in services.repositoryClient.repository.getBooksDetailsByTagId(id: categoryMap[2]!) {
        await MainActor.run {
          self.state.curatedCategories[1].books = Array(classicBooks.map { BookDetails(
            bookDetails: $0,
            colorProvider: self.services.colorProvider.colors)
            .bookCover }.prefix(8))
        }
      }
    }
  }
  
  private func observeKidsBooks() {
    Task {
      for await kidsBooks in services.repositoryClient.repository.getBooksDetailsByTagId(id: categoryMap[3]!) {
        await MainActor.run {
          self.state.curatedCategories[2].books = Array(kidsBooks.map { BookDetails(
            bookDetails: $0,
            colorProvider: self.services.colorProvider.colors)
            .bookCover }.prefix(8))
        }
      }
    }
  }
}
