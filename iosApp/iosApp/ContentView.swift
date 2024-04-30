import SwiftUI
import Combine
import Shared

struct ContentView: View {
    @State private var homeViewModel: HomeViewModel = Koin.instance.get()
    @State var state: HomeScreenUiState = HomeScreenUiState.Loading(books: [])
    @State private var showContent = false
    var body: some View {
        VStack {
            Button("Click me!") {
                homeViewModel.reloadData()
                withAnimation {
                    showContent = !showContent
                }
            }
            
            if state is HomeScreenUiState.Success {
                Text("Success")
                let s = state as! HomeScreenUiState.Success
                ForEach(s.books, id: \.uuid) { book in
                    Text(book.title)
                }
            } else {
                Text("Loading or Error")
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)
                    Text("SwiftUI: \(Greeting().greet())")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
            
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
        .task {
            for await stateData in homeViewModel.uiState {
                state = stateData
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
