//
//  CategoryView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CategoryView<Content: View>: View {
  private let title: String
  @ViewBuilder var content: () -> Content
  
  init(title: String, @ViewBuilder content: @escaping () -> Content) {
    self.title = title
    self.content = content
  }
  var body: some View {
    VStack(alignment: .leading, spacing: 0) {
      Text(title)
      content()
    }
  }
}
