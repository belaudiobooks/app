//
//  ColorToken.swift
//  iosApp
//
//  Created by Sergey Prybysh on 11/18/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import UIKit

struct ColorToken {
  enum Theme {
    case light
    case dark
  }
  
  let name: String
  let color: Color
  
  init(name: String, lightColor: Color, darkColor: Color) {
    self.name = name
    let uiColor: UIColor = UIColor { trait -> UIColor in
      return trait.userInterfaceStyle == .light ? UIColor(lightColor) : UIColor(darkColor)
    }
    
    self.color = Color(uiColor: uiColor)
  }
}

private extension UIColor {
  convenience init(_ color: Color) {
     if let cgColor = color.cgColor {
         self.init(cgColor: cgColor)
     } else {
         self.init(white: 0.0, alpha: 1.0)
     }
   }
}
