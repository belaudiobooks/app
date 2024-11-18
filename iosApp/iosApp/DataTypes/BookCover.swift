//
//  BookCover.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

struct BookCover: Equatable, Identifiable {
  var id: String { uuid }
  let uuid: String
  let title: String
  let coverImageURL: [String]
  let description: String
  let authors: [Author]
  let tags: [String]
}

extension BookCover {
  init(bookCover: Shared.BookCover) {
    uuid = bookCover.uuid
    title = bookCover.title
    coverImageURL = [bookCover.coverImage]
    description = bookCover.description_
    authors = bookCover.authors.map { .init(author: $0) }
    tags = bookCover.tags.map { $0.name }
  }
  
  init(bookDetails: BookDetails) {
    uuid = bookDetails.uuid
    title = bookDetails.title
    coverImageURL = bookDetails.narrations.map { $0.coverImageURL }
    description = bookDetails.description
    authors = bookDetails.authors
    tags = bookDetails.tags
  }
}

extension Kotlinx_datetimeLocalDate {
  var date: Date? {
    var dateComponents = DateComponents()
    dateComponents.year = Int(year)
    dateComponents.month = Int(month.ordinal)
    dateComponents.day = Int(dayOfMonth)
    
    let calendar = Calendar.current
    return calendar.date(from: dateComponents)
  }
}
