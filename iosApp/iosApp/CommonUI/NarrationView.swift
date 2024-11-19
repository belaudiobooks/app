//
//  NarrationView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 11/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct NarrationView: View {
  let narration: NarrationDetails
    var body: some View {
      HStack(spacing: 12) {
        BookCoverImageView(imageURL: narration.coverImageURL)
          .frame(width: 180, height: 180)
        VStack(alignment: .leading, spacing: 8) {
          NarrationTextContainerView(title: "Narrator:", description: narration.narrator)
          NarrationTextContainerView(title: "Publisher:", description: narration.publisher)
          NarrationTextContainerView(title: "Language:", description: narration.language)
          NarrationTextContainerView(title: "Cost:", description: narration.cost ? "Paid" : "Free")
          Text(narration.duration)
          Text("\(narration.streamingServices.count) services")
        }
        .font(.footnote)
      }
    }
}

struct NarrationTextContainerView: View {
  let title: String
  let description: String
  
  var body: some View {
    HStack(spacing: 4) {
      Text(title)
        .bold()
      Spacer()
      
      Text(description)
    }
  }
}

#Preview {
  NarrationView(narration: .init(
    uuid: UUID().uuidString,
    coverImageURL: "https://storage.googleapis.com/books_media/covers/orden-belai-myshy-eb2d7db0-37ac-4ed4-b1fd-6e47bf991f87.jpeg",
    narrator: "Іван Свістулькін",
    publisher: "audiobooks.by",
    language: "беларуская",
    duration: "16 гадзін 52 хвіліны",
    cost: true,
    streamingServices: []))
}
