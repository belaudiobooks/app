//
//  ColorProvider.swift
//  iosApp
//
//  Created by Sergey Prybysh on 1/26/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import UIKit
import SwiftUI

class ColorProvider {
  private var availableColors: [(Color, Color)] = [
    (Color.pink.opacity(0.8), Color.purple.opacity(0.8)),
    (Color.blue.opacity(0.6), Color.black.opacity(0.8)),
    (Color.red.opacity(0.8), Color.pink.opacity(0.4)),
  ]
  
  private var colorCache: [String: (Color, Color)] = [:]
  private let storageKey = "ColorProviderStorage"

  init() {
    if let storedData = UserDefaults.standard.data(forKey: storageKey),
       let storedColors = try? JSONDecoder().decode([String: [[Double]]].self, from: storedData) {
      for (uuid, colorComponents) in storedColors {
        if colorComponents.count == 2 {
          let color1 = Color(red: colorComponents[0][0], green: colorComponents[0][1], blue: colorComponents[0][2])
          let color2 = Color(red: colorComponents[1][0], green: colorComponents[1][1], blue: colorComponents[1][2])
          colorCache[uuid] = (color1, color2)
        }
      }
    }
  }

  func colors(for uuid: String) -> (Color, Color) {
    if let cachedColors = colorCache[uuid] {
      return cachedColors
    }
    
    let number = Int.random(in: 0...2)
    colorCache[uuid] = availableColors[number]
    saveToDisk()
    
    return availableColors[number]
  }

  private func saveToDisk() {
    var persistentData: [String: [[Double]]] = [:]
    
    for (uuid, colors) in colorCache {
      persistentData[uuid] = [
        colors.0.toComponents(),
        colors.1.toComponents()
      ]
    }
    
    if let encodedData = try? JSONEncoder().encode(persistentData) {
      UserDefaults.standard.set(encodedData, forKey: storageKey)
    }
  }
}

private extension Color {
  func toComponents() -> [Double] {
    let uiColor = UIColor(self)
    var red: CGFloat = 0
    var green: CGFloat = 0
    var blue: CGFloat = 0
    var alpha: CGFloat = 0
    uiColor.getRed(&red, green: &green, blue: &blue, alpha: &alpha)
    return [Double(red), Double(green), Double(blue)]
  }
}
