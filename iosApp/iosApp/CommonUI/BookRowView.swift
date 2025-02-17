//
//  BookRowView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct BookRowView<Context: View>: View {
  var books: [BookCover]
  @ViewBuilder var context: (BookCover) -> Context
  
  var body: some View {
    ScrollView(.horizontal, showsIndicators: false) {
      HStack(spacing: 12) {
        ForEach(books) { book in
          context(book)
        }
      }
      .scrollTargetLayout()
    }
    .padding(.horizontal, 12)
    .padding(.vertical, 16)
    .scrollTargetBehavior(.viewAligned)
  }
}
