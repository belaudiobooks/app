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
  private let onTapAction: (() -> Void)?
  
  init(
    title: String,
    onTapAction: (() -> Void)? = nil,
    @ViewBuilder content: @escaping () -> Content) {
    self.title = title
    self.content = content
    self.onTapAction = onTapAction
  }
  
  var body: some View {
    VStack(alignment: .leading, spacing: 0) {
      
      HStack(spacing: 4) {
        Text(title)
          .font(._title3)
        
        if onTapAction != nil {
          Image(systemName: "chevron.forward")
            .font(._iconSemibold)
            .foregroundColor(.gray)
            .padding(.leading, 8)
        }
        
        Spacer()
      }
      .padding(.horizontal, 20)
      .onTapGesture {
        onTapAction?()
      }
      
      content()
    }
  }
}
