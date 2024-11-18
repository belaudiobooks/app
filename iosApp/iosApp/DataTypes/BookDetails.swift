//
//  BookDetails.swift
//  iosApp
//
//  Created by Sergey Prybysh on 11/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

struct BookDetails: Equatable, Identifiable {
  var id: String { uuid }
  let uuid: String
  let title: String
  let narrations: [NarrationDetails]
  let description: String
  let authors: [Author]
  let tags: [String]
}

extension BookDetails {
  var bookCover: BookCover {
    BookCover(bookDetails: self)
  }
}

extension BookDetails {
  init(bookDetails: Shared.BookDetails) {
    uuid = bookDetails.uuid
    title = bookDetails.title
    narrations = bookDetails.narrations.map { .init(narrationDetails: $0) }
    description = bookDetails.description_
    authors = bookDetails.authors.map { .init(author: $0) }
    tags = bookDetails.tags.map { $0.name }
  }
}
