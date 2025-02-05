//
//  BookGridView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 1/26/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct BookGridView: View {
  private let columns = [
    GridItem(.flexible()),
    GridItem(.flexible())
  ]
  
  var books: [BookCover]
  var action: (String) -> Void

  var body: some View {
    ScrollView {
      LazyVGrid(columns: columns, spacing: 8) {
        ForEach(books) { bookCover in
          BookCoverView(
            book: bookCover,
            action: action)
        }
      }
      .padding(16)
    }
  }
}
