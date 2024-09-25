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
  let coverImageURL: String
  let date: Date?
  let description: String
  let descriptionSource: String
  let authors: [String]
  let tags: [String]
}

extension BookCover {
  init(bookCover: Shared.BookCover) {
    uuid = bookCover.uuid
    title = bookCover.title
    coverImageURL = bookCover.coverImage
    date = bookCover.date.date
    description = bookCover.description
    descriptionSource = bookCover.descriptionSource
    // TODO: Create types
    authors = bookCover.authors.map { $0.name }
    tags = bookCover.tags.map { $0.name }
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
