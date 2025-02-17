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
      ScrollView(.vertical) {
        VStack {
          CategoryView(title: "New", onTapAction: nil) {
            BookRowView(books: store.state.newCategory) { book in
              BookGrandCoverView(
                bookID: book.uuid,
                bookImage: book.bookImages.first!,
                action: { _ in store.handle(.selctedBookCover(id: book.uuid)) })
            }
          }
          
          ForEach(Array(store.state.curatedCategories.enumerated()), id: \.1.id) { index, category in
            CategoryView(title: category.name, onTapAction: { store.handle(.selectedCategory(sectionIndex: index + 1)) }) {
              BookRowView(books: category.books) { book in
                BookCoverView(
                  book: book,
                  action: { _ in store.handle(.selctedBookCover(id: book.uuid)) })
              }
            }
          }
        }
      }
      .padding(.top, 16)
      .navigationTitle("Books")
    }
    .navigationDestination(for: BookDetailsComponent.self) { $0.view }
    .navigationDestination(for: BookCollectionComponent.self) { $0.view }
  }
}
