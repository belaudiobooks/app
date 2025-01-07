//
//  Fonts.swift
//  iosApp
//
//  Created by Sergey Prybysh on 11/18/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

extension Font {
  static var _title1: Font {
    .system(size: 26, weight: .semibold)
  }
  
  static var _title2: Font {
    .system(size: 23, weight: .semibold)
  }
  
  static var _title3: Font {
    .system(size: 20, weight: .semibold)
  }
  
  static var _subtitle: Font {
    .system(size: 17, weight: .semibold)
  }
  
  static var _textLarge: Font {
    .system(size: 17, weight: .regular)
  }
  
  static var _textRegular: Font {
    .system(size: 15, weight: .regular)
  }
  
  static var _iconSemibold: Font {
    .system(size: 15, weight: .semibold)
  }
}
