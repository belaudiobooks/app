//
//  StreamingService.swift
//  iosApp
//
//  Created by Sergey Prybysh on 11/13/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

struct StreamingService: Equatable {
  let name: String
  let caption: String
  let imageURLString: String
  let availability: String
}

extension StreamingService {
  init(linkDetails: Shared.LinkDetails) {
    name = linkDetails.linkTypeName
    caption = linkDetails.linkTypeCaption
    imageURLString = linkDetails.linkTypeIcon
    availability = linkDetails.linkTypeAvailability.name
  }
}
