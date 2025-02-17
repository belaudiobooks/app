//
//  BookGrandCoverView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 2/16/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct BookGrandCoverView: View {
  let bookID: String
  let bookImage: BookImage
  var action: (String) -> Void
  
  var body: some View {
    BookGrandImageView(bookImage: bookImage)
    .onTapGesture {
      action(bookID)
    }
  }
}

#Preview {
    BookGrandCoverView(
      bookID: "123",
      bookImage: BookImage(
        imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
        authorName: "Іван Свістулькін",
        title: "Доўгая дарога дадому",
        gradientColors: [.pink, .purple]),
      action: {_ in }
    )
}
