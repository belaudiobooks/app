//
//  BookImage.swift
//  iosApp
//
//  Created by Sergey Prybysh on 1/11/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct BookImage: Equatable {
  let imageURL: String
  // Following data needed for a fallback state
  let authorName: String
  let title: String
  let gradientColors: [Color]
}
