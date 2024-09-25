//
//  Category.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

struct Category: Equatable, Identifiable {
  var id: String { name }
  let name: String
  let books: [BookCover]
}
