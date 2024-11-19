//
//  NarrationDetails.swift
//  iosApp
//
//  Created by Sergey Prybysh on 11/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

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
}

extension NarrationDetails {
  init(narrationDetails: Shared.NarrationDetails) {
    uuid = narrationDetails.bookUuid
    coverImageURL = narrationDetails.coverImage
    narrator = narrationDetails.narrators.first?.name ?? ""
    publisher = narrationDetails.publishers.first?.name ?? ""
    language = narrationDetails.language.name.capitalized
    duration = narrationDetails.duration.description
    cost = narrationDetails.paid
    streamingServices = narrationDetails.links.map { .init(linkDetails: $0) }
  }
}
