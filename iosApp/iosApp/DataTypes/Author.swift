//
//  Author.swift
//  iosApp
//
//  Created by Sergey Prybysh on 11/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

struct Author: Equatable {
  let uuid: String
  let name: String
}

extension Author {
  init(author: Shared.Person) {
    uuid = author.uuid
    name = author.name
  }
}
