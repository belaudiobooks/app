//
//  HomeView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HomeView: ComponentView {
  @ObservedObject var store: Store<HomeViewState, HomeViewAction>
  
  var body: some View {
    NavigationView {
      List {
        CategoryView(title: "New") {
          BookRowView(books: store.state.newCategory) { bookID in
            store.send(.selctedBookCover(id: bookID))
          }
        }
        
        ForEach(store.curatedCategories, id: \.id){ category in
          CategoryView(title: category.name) {
            BookRowView(books: category.books) { bookID in
              store.send(.selctedBookCover(id: bookID))
            }
          }
        }
      }
      .navigationTitle("Books")
    }
    .sheet(
      store: self.store.scope(
        state: \.$bookDetailsState,
        action: { .bookDetails($0) }),
      content: { store in
        BookDetailsView(store: store) }
    )
  }
}
