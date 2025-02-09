//
//  BookCover.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared
import SwiftUI

struct BookCover: Equatable, Identifiable {
  var id: String { uuid }
  let uuid: String
  let title: String
  let bookImages: [BookImage]
  let authorsName: String
}

extension BookCover {
  init(bookCover: Shared.BookCover, gradientColorProvider: (String) -> (Color, Color)) {
    uuid = bookCover.uuid
    title = bookCover.title
    let colors = gradientColorProvider(bookCover.uuid)
    bookImages = [
      BookImage(
        imageURL: String(bookCover.coverImage),
        authorName: bookCover.authors[0].name,
        title: bookCover.title,
        gradientColors: [colors.0, colors.1])
    ]
    authorsName = bookCover.authors.map { $0.name }.first ?? ""
  }
  
  init(bookDetails: BookDetails) {
    uuid = bookDetails.uuid
    title = bookDetails.title
    bookImages = bookDetails.narrations.map { $0.bookImage }
    authorsName = bookDetails.authors.map { $0.name }.first ?? ""
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
