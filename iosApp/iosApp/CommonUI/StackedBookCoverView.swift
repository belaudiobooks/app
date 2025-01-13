//
//  StackedBookCoverView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/24/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct StackedBookCoverView: View {
  let bookImages: [BookImage]
  
  var totalOffset: Double { 10 * Double((bookImages.count - 1)) }
  var itemSize: Double { 150 - totalOffset }
  
  var body: some View {
    ZStack {
      ForEach(0..<bookImages.count, id: \.self) { index in
        BookCoverImageView(bookImage: bookImages[index])
          .frame(width: itemSize, height: itemSize)
          .offset(x: CGFloat(index) * 10, y: CGFloat(index) * 10)
      }
    }
    .padding(.trailing, CGFloat(totalOffset))
    .padding(.bottom, CGFloat(totalOffset))
  }
}

#Preview {
  VStack(alignment: .leading) {
    StackedBookCoverView(bookImages: [
      BookImage(
        imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
        authorName: "Іван Свістулькін",
        title: "Доўгая дарога дадому",
        gradientColors: [.pink, .purple]),
      BookImage(
        imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
        authorName: "Іван Свістулькін",
        title: "Доўгая дарога дадому",
        gradientColors: [.pink, .purple]),
      BookImage(
        imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
        authorName: "Іван Свістулькін",
        title: "Доўгая дарога дадому",
        gradientColors: [.pink, .purple]),
    ])
    
    StackedBookCoverView(bookImages: [
      BookImage(
        imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
        authorName: "Іван Свістулькін",
        title: "Доўгая дарога дадому",
        gradientColors: [.pink, .purple]),
      BookImage(
        imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
        authorName: "Іван Свістулькін",
        title: "Доўгая дарога дадому",
        gradientColors: [.pink, .purple])
    ])
    
    StackedBookCoverView(bookImages: [
      BookImage(
        imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
        authorName: "Іван Свістулькін",
        title: "Доўгая дарога дадому",
        gradientColors: [.pink, .purple]),
    ])
  }
}
