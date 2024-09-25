//
//  StackedBookCoverView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/24/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct StackedBookCoverView: View {
  let imageURLs: [String]
  
  var totalOffset: Double { 10 * Double((imageURLs.count - 1)) }
  var itemSize: Double { 150 - totalOffset }
  
  var body: some View {
    ZStack {
      ForEach(0..<imageURLs.count, id: \.self) { index in
        BookCoverImageView(imageURL: imageURLs[index])
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
    StackedBookCoverView(imageURLs: [
      "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
      "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
      "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg"
    ])
    
    StackedBookCoverView(imageURLs: [
      "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
      "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg"
    ])
    
    StackedBookCoverView(imageURLs: [
      "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg"
    ])
  }
}
