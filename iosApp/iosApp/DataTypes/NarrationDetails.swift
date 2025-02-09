//
//  NarrationDetails.swift
//  iosApp
//
//  Created by Sergey Prybysh on 11/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared
import SwiftUI

struct NarrationDetails: Equatable, Identifiable {
  var id: String { uuid }
  let uuid: String
  let coverImageURL: String
  let narrator: String
  let publisher: String
  let language: String
  let duration: String
  let cost: Bool
  let streamingServices: [StreamingService]
  let authorsName: String
  let bookTitle: String
  let fallbackColorGradient: [Color]
  
  var bookImage: BookImage {
    .init(imageURL: coverImageURL, authorName: authorsName, title: bookTitle, gradientColors: fallbackColorGradient)
  }
}

extension NarrationDetails {
  init(
    narrationDetails: Shared.NarrationDetails,
    bookTitle: String,
    authorsName: String,
    colorProvider: (String) -> (Color, Color)) {
    uuid = narrationDetails.bookUuid
    coverImageURL = narrationDetails.coverImage
    narrator = narrationDetails.narrators.first?.name ?? ""
    publisher = narrationDetails.publishers.first?.name ?? ""
    language = narrationDetails.language.name.capitalized
    duration = narrationDetails.duration.description
    cost = narrationDetails.paid
    streamingServices = narrationDetails.links.map { .init(linkDetails: $0) }
    self.authorsName = authorsName
    self.bookTitle = bookTitle
    let colors = colorProvider(narrationDetails.bookUuid)
    fallbackColorGradient = [colors.0, colors.1]
  }
}
