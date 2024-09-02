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
      AsyncImage(url: URL(string: book.coverImageURL)) { phase in
        switch phase {
        case .failure:
          Image(systemName: "photo")
            .font(.largeTitle)
        case .success(let image):
          image
            .resizable()
        default:
          ProgressView()
        }
      }
      .frame(width: 150, height: 150)
      .clipShape(.rect(cornerRadius: 10))
      Text(book.title)
        .font(.caption)
      Text("will be added later")
        .font(.caption2)
        .foregroundColor(.gray)
    }
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
      coverImageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
      date: Date(),
      description: "Description One",
      descriptionSource: "Source One",
      authors: [],
      tags: []),
    action: {_ in })
}
