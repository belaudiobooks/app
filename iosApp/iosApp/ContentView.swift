import SwiftUI

struct ContentView: View {
    struct Success {
        var showContent: Bool
        let greeting: String
    }
    
    struct Error {
        let reason: String
    }
    
    // Could be replaced by StateHolder
    @ObservedObject var viewModel: ContentViewModel
    
    var body: some View {
        VStack {
            switch viewModel.state {
            // Handle successful state
            case let .success(successState):
                if successState.showContent {
                    VStack(spacing: 16) {
                        Image(systemName: "swift")
                            .font(.system(size: 200))
                            .foregroundColor(.accentColor)
                        Text("SwiftUI: \(successState.greeting)")
                    }
                    .transition(.move(edge: .top).combined(with: .opacity))
                } else {
                    Text("Hidden...")
                }
               
            // Handle error state
            case let .error(errorState):
                Text("Error: \(errorState.reason)")
                
            // Handle loading state
            case  .loading:
                Text("Loading...")
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
        .padding()
      
      Button("Click me!") {
          viewModel.handle(action: .didTapShowContent)
      }
      .padding(.bottom, 200)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: ContentViewModel(state: .success(.init(showContent: true, greeting: "Hello"))))
    }
}
