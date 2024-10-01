//
//  BookRowView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct BookRowView: View {
  var books: [BookCover]
  var action: (String) -> Void
  
  var body: some View {
    ScrollView(.horizontal, showsIndicators: false) {
      LazyHStack {
        ForEach(books) { book in
          BookCoverView(book: book) { bookId in
            action(bookId)
          }
        }
      }
    }.border(Color.black)
  }
}
