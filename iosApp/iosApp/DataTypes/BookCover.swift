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
  let bookImages: [BookImage]
  let description: String
  let authors: [Author]
  let tags: [String]
}

extension BookCover {
  init(bookCover: Shared.BookCover) {
    print(bookCover.coverImage)
    uuid = bookCover.uuid
    title = bookCover.title
    let urls: String = bookCover.coverImage
    bookImages = [BookImage(imageURL: String(bookCover.coverImage), authorName: bookCover.authors[0].name, title: bookCover.title, gradientColors: [.pink, .purple])]
    description = bookCover.description_
    authors = bookCover.authors.map { .init(author: $0) }
    tags = bookCover.tags.map { $0.name }
  }
  
  init(bookDetails: BookDetails) {
    uuid = bookDetails.uuid
    title = bookDetails.title
    bookImages = bookDetails.narrations.map { $0.bookImage }
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
