//
//  HomeView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HomeView: ComponentView {
  @ObservedObject var store: Store<HomeViewModel.State, HomeViewModel.Action>
  
  var body: some View {
    NavigationView {
      List {
        CategoryView(title: "New") {
          BookRowView(books: store.state.newCategory) { bookID in
            store.handle(.selctedBookCover(id: bookID))
          }
        }
        
        ForEach(store.state.curatedCategories, id: \.id){ category in
          CategoryView(title: category.name) {
            BookRowView(books: category.books) { bookID in
              store.handle(.selctedBookCover(id: bookID))
            }
          }
        }
      }
      .navigationTitle("Books")
    }
    .navigationDestination(for: BookDetailsComponent.self) { $0.view }
  }
}
