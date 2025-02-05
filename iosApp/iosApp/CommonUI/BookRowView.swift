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
      HStack(spacing: 0) {
        ForEach(books) { book in
          BookCoverView(book: book) { bookId in
            action(bookId)
          }
        }
      }
    }
  }
}
