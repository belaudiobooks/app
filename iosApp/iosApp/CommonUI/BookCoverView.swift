//
//  BookCoverView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct BookCoverView: View {
  let book: BookCover
  var action: (String) -> Void
  
  var body: some View {
    VStack(alignment: .leading) {
      StackedBookCoverView(bookImages: book.bookImages)
      Text(book.title)
        .font(.caption)
        .lineLimit(2)
      Text("will be added later")
        .font(.caption2)
        .foregroundColor(.gray)
        .lineLimit(2)
    }
    .frame(maxWidth: 150)
    .padding()
    .onTapGesture {
      action(book.id)
    }
  }
}


#Preview {
  BookCoverView(
    book: BookCover(
      uuid: "1",
      title: "Book One",
      bookImages: [BookImage(
        imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
        authorName: "Іван Свістулькін",
        title: "Доўгая дарога дадому",
        gradientColors: [.pink, .purple])],
      description: "Description One",
      authors: [],
      tags: []),
    action: {_ in })
}
