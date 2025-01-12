//
//  BookCoverImageView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/24/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct BookCoverImageView: View {
  let imageURL: String
  let fallbackState: FallbackView.State
  
  var body: some View {
    VStack(alignment: .leading) {
      AsyncImage(url: URL(string: imageURL)) { phase in
        switch phase {
        case .failure:
          FallbackView(state: fallbackState)
        case .success(let image):
          image
            .resizable()
        default:
          ProgressView()
        }
      }
      .clipShape(.rect(cornerRadius: 10))
    }
  }
}

struct FallbackView: View {
  struct State {
    let authorName: String
    let title: String
    let color: Color
  }
  
  let state: State
  
  var body: some View {
    ZStack {
      Color.purple
        .ignoresSafeArea()
        .overlay(
          LinearGradient(colors: [.purple, .pink], startPoint: .top, endPoint: .bottom)
            .opacity(0.3)
        )
        .background(
          Rectangle()
            .fill(Color.white.opacity(0.05))
            .blur(radius: 20)
        )
      
      VStack(spacing: 20) {
        Text(state.authorName)
          .font(._title3)
          .foregroundColor(.white)
        
        Rectangle()
          .fill(Color.purple)
          .frame(height: 4)
          .frame(maxWidth: 150)
        
        Text(state.title)
          .font(._title3)
          .fontWeight(.bold)
          .foregroundColor(.white)
      }
    }
  }
}

#Preview {
  BookCoverImageView(
    imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
    fallbackState: .init(
      authorName: "Іван Свістулькін",
      title: "Доўгая дарога дадому",
      color: .green))
}

#Preview {
  BookCoverImageView(
    imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava.jpg",
    fallbackState: .init(
      authorName: "Іван Свістулькін",
      title: "Доўгая дарога дадому",
      color: .green))
}
