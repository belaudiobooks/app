//
//  BookGrandImageView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 2/8/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct BookGrandImageView: View {
  let bookImage: BookImage
  
  var body: some View {
    VStack(alignment: .leading) {
      ABAsyncImageView(
        url: URL(string: bookImage.imageURL),
        content: { image in image.resizable()},
        loading: { ProgressView() },
        error: { FallbackView(
          authorName: bookImage.authorName,
          title: bookImage.title,
          gradientColors: bookImage.gradientColors) })
      
      
      .clipShape(.rect(cornerRadius: 10))
    }
  }
}

#Preview {
    BookGrandImageView(bookImage: .init(
      imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
      authorName: "Іван Свістулькін",
      title: "Доўгая дарога дадому",
      gradientColors: [.pink, .purple]))
}
