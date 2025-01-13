//
//  BookCoverImageView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/24/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct BookCoverImageView: View {
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

struct FallbackView: View {
  let authorName: String
  let title: String
  let gradientColors: [Color]
  
  var body: some View {
    ZStack {
      Color.purple
        .ignoresSafeArea()
        .overlay(
          LinearGradient(colors: gradientColors, startPoint: .top, endPoint: .bottom)
            .opacity(0.3)
        )
        .background(
          Rectangle()
            .fill(Color.white.opacity(0.05))
            .blur(radius: 20)
        )
      
      VStack(spacing: 20) {
        Text(authorName)
          .font(._title3)
          .foregroundColor(.white)
        
        Rectangle()
          .fill(Color.purple)
          .frame(height: 4)
          .frame(maxWidth: 150)
        
        Text(title)
          .font(._title3)
          .fontWeight(.bold)
          .foregroundColor(.white)
      }
    }
  }
}

#Preview {
  BookCoverImageView(bookImage: .init(
    imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
    authorName: "Іван Свістулькін",
    title: "Доўгая дарога дадому",
    gradientColors: [.pink, .purple]))
}

#Preview {
  BookCoverImageView(bookImage: .init(
    imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava.jpg",
    authorName: "Іван Свістулькін",
    title: "Доўгая дарога дадому",
    gradientColors: [.pink, .purple]))
}
