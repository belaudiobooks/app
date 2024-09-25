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
  
  var body: some View {
    VStack(alignment: .leading) {
      AsyncImage(url: URL(string: imageURL)) { phase in
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
      .clipShape(.rect(cornerRadius: 10))
    }
  }
}


#Preview {
  BookCoverImageView(imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg")
}
