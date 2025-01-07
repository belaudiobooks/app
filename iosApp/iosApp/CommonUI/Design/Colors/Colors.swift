//
//  Colors.swift
//  iosApp
//
//  Created by Sergey Prybysh on 11/18/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

extension ColorToken {
  static let primaryText = ColorToken(
    name: "primary_text",
    lightColor: Color(white: 0.1),
    darkColor: Color(white: 0.9))
  
  static let secondaryText = ColorToken(
    name: "secondary_text",
    lightColor: Color(white: 0.6),
    darkColor: Color(white: 0.7))
}

extension Color {
  static var _primaryText: Color {
    ColorToken.primaryText.color
  }
  
  static var _secondaryText: Color {
    ColorToken.secondaryText.color
  }
}
